package com.cg.controller.API;

import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.model.*;
import com.cg.model.dto.product.ProductCreateDTO;
import com.cg.model.dto.product.ProductDTO;
import com.cg.model.dto.product.ProductUpdateDTO;

import com.cg.service.product.IProductService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {
    @Autowired
    private IProductService productService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<?> getAllByDeletedIsFalse() {
        List<ProductDTO> productDTOS = productService.getAllProductDTO();
        if (productDTOS.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getById(@PathVariable String productId) {
        long pid;
        try {
            pid = Long.parseLong(productId);
        } catch (NumberFormatException e) {
            throw new DataInputException("Product ID does not match, please re-enter!");
        }

        Optional<Product> productOptional = productService.findById(pid);

        if (!productOptional.isPresent()) {
            throw new DataInputException("Product ID does not match, please re-enter!");
        }

        return new ResponseEntity<>(productOptional.get().toProductDTO(), HttpStatus.OK);
    }




    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> create(@Validated ProductCreateDTO productCreateDTO, BindingResult bindingResult) {
        new ProductCreateDTO().validate(productCreateDTO, bindingResult);
        MultipartFile imageFile = productCreateDTO.getFile();

        if (imageFile == null) {
            throw new DataInputException("Please choose an image!");
        }

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        if (productService.existsByProductName(productCreateDTO.getProductName())) {
            throw new EmailExistsException("The product already exists in the system, please re-enter it!");
        }

        productCreateDTO.setId(null);
        Product newProduct = productService.createWithAvatar(productCreateDTO);

        return new ResponseEntity<>(newProduct.toProductDTO(), HttpStatus.CREATED);
    }



    @PatchMapping("/update/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> update(@PathVariable Long productId, MultipartFile file, @Validated ProductUpdateDTO productUpdateDTO, BindingResult bindingResult) {
        Optional<Product> productOptional = productService.findById(productId);
        if (!productOptional.isPresent()) {
            throw new DataInputException("Product ID does not exist, please re-enter! ");
        }

        Product product = productOptional.get();

        if (productService.existsByProductNameAndIdNot(productUpdateDTO.getProductName(), productId)) {
            throw new DataInputException("The product already exists in the system, please re-enter it!");
        }

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        String productName = productUpdateDTO.getProductName();
        BigDecimal price = new BigDecimal(Long.parseLong(productUpdateDTO.getPrice()));
        int quantity = Integer.parseInt(productUpdateDTO.getQuantity());
        String description = productUpdateDTO.getDescription();

        product.setProductName(productName)
                .setPrice(price)
                .setQuantity(quantity)
                .setDescription(description);
        product = productService.save(product);

        if(file != null){
            product = productService.saveWithAvatar(product, file);
        }
        return new ResponseEntity<>(product.toProductDTO(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long productId) {

        Optional<Product> productOptional = productService.findById(productId);

        if (!productOptional.isPresent()) {
            throw new DataInputException("Invalid product ID, please re-enter!");
        }
//        if (bindingResult.hasFieldErrors()) {
//            return appUtils.mapErrorToResponse(bindingResult);
//        }

        try {
            productService.softDelete(productId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new DataInputException("Please contact Administrator!");
        }
    }

}
