package com.utrechtfour.supermarket.dto;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.utrechtfour.supermarket.model.PurchaseOrderItem;
import com.utrechtfour.supermarket.views.RestViews;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class PurchaseOrderItemDTO {

        @JsonView({RestViews.PurchaseOrderView.class})
        @JsonProperty("id")
        private Long purchaseOrderItemId;
        @JsonView({RestViews.PurchaseOrderView.class})
        @JsonProperty("product_id")
        private Long productId;
        @JsonProperty("quantity")
        @JsonView({RestViews.PurchaseOrderView.class})
        private Integer quantity;
        @JsonView({RestViews.PurchaseOrderView.class})
        @JsonProperty("price")
        @NumberFormat(pattern = "000.00")
        private BigDecimal purchasePrice;

        public Long getPurchaseOrderItemId() {
            return purchaseOrderItemId;
        }

        public void setPurchaseOrderItemId(Long purchaseOrderItemId) {
            this.purchaseOrderItemId = purchaseOrderItemId;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getPurchasePrice() {
            return purchasePrice;
        }

        public void setPurchasePrice(BigDecimal purchasePrice) {
            this.purchasePrice = purchasePrice;
        }

        public static PurchaseOrderItemDTO convertToDTO(PurchaseOrderItem purchaseItem){
            PurchaseOrderItemDTO purchaseOrderItemDTO = new PurchaseOrderItemDTO();
            purchaseOrderItemDTO.setPurchaseOrderItemId(purchaseItem.getId());
            purchaseOrderItemDTO.setQuantity(purchaseItem.getQuantity());
            purchaseOrderItemDTO.setPurchasePrice(purchaseItem.getPurchasePrice());
            purchaseOrderItemDTO.setProductId(purchaseItem.getProduct().getId());
            return purchaseOrderItemDTO;
        }

        public static Set<PurchaseOrderItemDTO> convertToDTOSet(Set<PurchaseOrderItem> items){
            Set<PurchaseOrderItemDTO> purchaseOrderItemDTOS = new HashSet<>();
            for(PurchaseOrderItem item: items){
                purchaseOrderItemDTOS.add(convertToDTO(item));
            }
            return purchaseOrderItemDTOS;
        }

    }

