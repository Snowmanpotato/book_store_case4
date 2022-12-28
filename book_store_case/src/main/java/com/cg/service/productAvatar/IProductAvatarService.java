package com.cg.service.productAvatar;

import com.cg.model.ProductAvatar;
import com.cg.service.IGeneralService;

public interface IProductAvatarService extends IGeneralService<ProductAvatar> {
    void delete(String id);
}
