package com.cg.model.dto.product;

import com.cg.model.Product;
import com.cg.model.ProductAvatar;
import com.cg.model.dto.productAvatar.ProductAvatarDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCreateDTO implements Validator {
    private Long id;
    @NotEmpty(message = "Please enter product name!")
    @Size(min = 5, max = 100, message = "Names can be between 5 and 100 characters long!")
    private String productName;

    @NotEmpty(message = "You cannot leave the price blank !")
    private String price;

    @NotEmpty(message = "You cannot leave the quantity blank !")
    private String quantity;
    @NotEmpty(message = "Please enter product description!")
    private String description;

    MultipartFile file;

    private String fileType;

    public Product toProduct(ProductAvatar productAvatar){
        return new Product()
                .setId(id)
                .setProductName(productName)
                .setPrice(new BigDecimal(Long.parseLong(price)))
                .setQuantity(Integer.parseInt(quantity))
                .setDescription(description)
                .setProductAvatar(productAvatar);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductCreateDTO productCreateDTO = (ProductCreateDTO) target;
        String price = productCreateDTO.getPrice();
        if (price != null && price.length() > 0) {
            if (price.length() > 9){
                errors.rejectValue("price", "price.max", "Maximum product price is 999,999,999 VND, please enter product price!");
                return;
            }

            if (price.length() < 5){
                errors.rejectValue("price", "price.min", "The lowest product price is 10,000 VND, please enter the product price!");
                return;
            }

            if (!price.matches("(^$|[0-9]*$)")){
                errors.rejectValue("price",  "price.number","Product price must be numeric, please enter product price!");
                return;
            }

        } else {
            errors.rejectValue("price",  "price.null", "Product cannot be blank, please enter product price!");
        }

        String quantity = productCreateDTO.getQuantity();
        if (quantity != null && quantity.length() > 0) {
            if (quantity.length() > 6){
                errors.rejectValue("quantity", "quantity.max","The maximum number of products is 999,999, please enter the product price!" );
                return;
            }

            if (!quantity.matches("(^$|[0-9]*$)")){
                errors.rejectValue("quantity",  "quantity.number","Product quantity must be numeric, please enter product price!");
                return;
            }
            Long quantityLong = Long.parseLong(quantity);
            if (quantityLong < 0){
                errors.rejectValue("quantity", "quantity.min","Product quantity cannot be negative, please enter product price!");
            }
        } else {
            errors.rejectValue("quantity",  "quantity.null", "Please enter product quantity, please enter product price!");
        }
    }
}
