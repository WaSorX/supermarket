
# supermarket

Supermarket is a REST-API allowing front-end developers to create super-market management applications.

## Product [/product/{id}]

### Get a Product [GET]

+ Response 200 (application/json)
    
        {
            "id": 1,
            "barcode": "2340002112286",
            "name": "Bananas",
            "description": "Bananas",
            "category": {
                "id": 1,
                "name": "Fruits"
            },
            "brand": {
                "id", 1,
                "name": "Chiquita"
            },
            "vatTarrif": "LOW",
            "unit": "KG",
            "price": 0.55,
            "brand": {
                "name": "Chiquita"
            },
            "suppliers": [
                {
                    "id": 9,
                    "name": "Fruits Supplier"
                },
                {
                    "id": 8,
                    "name": "Generic Supplier"
                }
            ]
        }
        
## Product Creation [/product/]

### Create a New Product [POST]

You may create your own product using this action. It takes a JSON
object containing the following properties.
#####Omitted properties will be converted to null if not mandatory.  

+ barcode (string - mandatory) - The product's barcode.
+ name (string - mandatory) - The product's name.
+ description (string - optional) - A short description of the product.
+ category (JSON object - mandatory) - The product's category id.
+ brand (JSON object/mandatory) - The product's brand id.
+ vatTarrif (number) - The product's VAT category, (NONE:0, ZERO:1, LOW:2, HIGH:3).
+ unit (number) - The product unit of measurement category, (UNSPECIFIED:0, PC:1, KG:2).
+ price (number) - The product's price.
+ suppliers (Array<json>) - The suppliers' ids associated with the product. 


+ Request (application/json)

            {
                "barcode": "1iia778n220k1",
                "name": "Bananas",
                "description": "These are some bananas",
                "category": {
                    "id": 1
                },
                "brand": {
                    "id": 1
                },
                "vatTarrif": 2,
                "unit": 2,
                "price": 1.25,
                "suppliers": [
                    {
                        "id": 1
                    },
                    {
                        "id": 2
                    }
                ]
            }
        
+ Response (body)

        {
            "id": 22,
            "barcode": "1iia778n220k1",
            "name": "Bananas",
            "description": "These are some bananas",
            "category": {
                "id": 1,
                "name": "Fruits
            },
            "brand": {
                "id": 1,
                "name": "Chiquita"
            },
            "vatTarrif": "LOW",
            "unit": "KG",
            "price": 1.25,
            
            "suppliers": [
                {
                    "id": 9,
                    "name": "Fruit Supplier"
                },
                {
                    "id": 8,
                    "name": "Generic Supplier"
                }
            ]
        }




## Product Change [/product/{id}]

### Update an Existing Product [PUT]

You may update an existing product using this action. It takes a JSON
object containing the following properties.
####Omitted properties will be converted to null if not mandatory.  

+ barcode (string - mandatory) - The product's barcode.
+ name (string - mandatory) - The product's name.
+ description (string - optional) - A short description of the product.
+ category (JSON object - mandatory) - The product's category id.
+ brand (JSON object/mandatory) - The product's brand id.
+ vatTarrif (number) - The product's VAT category, (NONE:0, ZERO:1, LOW:2, HIGH:3).
+ unit (number) - The product unit of measurement category, (UNSPECIFIED:0, PC:1, KG:2).
+ price (number) - The product's price.
+ suppliers (Array<JSON>) - The suppliers' ids associated with the product.


   + Request (application/json)

            {
                "barcode": "1iia778n220k1",
                "name": "Bananas",
                "description": "These are some bananas",
                "category": {
                    "id": 1
                },
                "brand": {
                    "id": 1
                },
                "vatTarrif": 2,
                "unit": 2,
                "price": 1.25,
                "suppliers": [
                    {
                        "id": 1
                    },
                    {
                        "id": 2
                    }
                              ]
                    }

   + Response 201 (body)

            {
                "id": 22,
                "barcode": "1234567891230",
                "name": "Bananas",
                "description": "These are some bananas",
                "category": {
                    "id": 1,
                    "name": "Fruits"
                },
                "brand": {
                    "id": 1,
                    "name": "Chiquita"
                },
                "vatTarrif": "LOW",
                "unit": "KG",
                "price": null,
                "suppliers": [
                    {
                        "id": 1,
                        "name": "Fruit supplier"
                    }
                ]
            }
            


### Patch an Existing Product [PATCH]

You may patch an existing product using this action. It takes a JSON
object any containing the following properties. Omitted properties will not be changed. 
  
+ barcode (string) - The product's barcode.
+ name (string) - The product's name.
+ description (string) - A short description of the product.
+ category (JSON object) - The product's category id.
+ brand (JSON object) - The product's brand id.
+ vatTarrif (number) - The product's VAT category, (NONE:0, ZERO:1, LOW:2, HIGH:3).
+ unit (number) - The product unit of measurement category, (UNSPECIFIED:0, PC:1, KG:2).
+ price (number) - The product's price.
+ suppliers (Array<JSON>) - The suppliers' ids associated with the product.


   + Request (application/json)

            {
                "barcode": "1iia778n220k1",
                "name": "Bananas",
                "description": "These are some bananas",
                "category": {
                    "id": 1
                },
                "brand": {
                    "id": 1
                },
                "vatTarrif": 2,
                "unit": 2,
                "price": 1.25,
                "suppliers": [
                    {
                        "id": 1
                    },
                    {
                        "id": 2
                    }
                              ]
                    }

   + Response 201 (body)

            {
                "id": 22,
                "barcode": "1234567891230",
                "name": "Bananas",
                "description": "These are some bananas",
                "category": {
                    "id": 1,
                    "name": "Fruits"
                },
                "brand": {
                    "id": 1,
                    "name": "Chiquita"
                },
                "vatTarrif": "LOW",
                "unit": "KG",
                "price": null,
                "suppliers": [
                    {
                        "id": 1,
                        "name": "Fruit supplier"
                    }
                ]
            }
            
## Category [/category/{id}]

### Get a Category [GET]

+ Response 200 (application/json)
    
        {
            "id": 15,
            "name": "Soups"
        }
        
## Category Creation [/category/]

### Create a New Category [POST]

You may create your own category using this action. It takes a JSON
object containing the following properties.
#####Omitted properties will be converted to null if not mandatory.  

+ name (string - mandatory) - The category's name.

+ Request (application/json)

            {
                "name": "Soups"
            }
        
+ Response (body)

        {
            "id": 15,
            "name": "Soups"
        }




## Category Change [/category/{id}]

### Update an Existing Category [PUT]

You may update an existing category using this action. It takes a JSON
object containing the following properties.
####Omitted properties will be converted to null if not mandatory.  

+ name (string - mandatory) - The product's name.

   + Request (application/json)

            {
             "name": "Soups",
            }

   + Response 201 (body)

            {
                "id": 15,
                "name": "Soups"
            }
            


### Patch an Existing Category [PATCH]

You may patch an existing category by using this action. It takes a JSON
object any containing the following properties. Omitted properties will not be changed. 
  
+ name (string) - The category's name.

   + Request (application/json)

            {
                "name": "Soups",
            }

   + Response 201 (body)

            {
                "id": 15,
                "name": "Soups"
            }
            
## Brand [/brand/{id}]

### Get a Brand [GET]

+ Response 200 (application/json)
    
        {
            "id": 15,
            "name": "Chiquita"
        }
        
## Brand Creation [/brand/]

### Create a New Brand [POST]

You may create your own brand using this action. It takes a JSON
object containing the following properties.
#####Omitted properties will be converted to null if not mandatory.  

+ name (string - mandatory) - The brand's name.

+ Request (application/json)

            {
                "name": "Chiquita"
            }
        
+ Response (body)

        {
            "id": 15,
            "name": "Chiquita"
        }




## Brand Change [/brand/{id}]

### Update an Existing Brand [PUT]

You may update an existing brand using this action. It takes a JSON
object containing the following properties.
####Omitted properties will be converted to null if not mandatory.  

+ name (string - mandatory) - The brand's name.

   + Request (application/json)

            {
             "name": "Chiquita",
            }

   + Response 201 (body)

            {
                "id": 15,
                "name": "Chiquita"
            }
            


### Patch an Existing Brand [PATCH]

You may patch an existing brand by using this action. It takes a JSON
object any containing the following properties. Omitted properties will not be changed. 
  
+ name (string) - The brand's name.

   + Request (application/json)

            {
                "name": "Chiquita",
            }

   + Response 201 (body)

            {
                "id": 15,
                "name": "Soups"
            }

## Supplier [/supplier/{id}]

### Get a Supplier [GET]

+ Response 200 (application/json)
    
        {
            "id": 15,
            "name": "Jason Statham"
        }
        
## Supplier Creation [/supplier/]

### Create a New Supplier [POST]

You may create your own supplier using this action. It takes a JSON
object containing the following properties.
#######Omitted properties will be converted to null if not mandatory.  

+ name (string - mandatory) - The supplier's name.

+ Request (application/json)

            {
                "name": "Jason Statham"
            }
        
+ Response (body)

        {
            "id": 15,
            "name": "Jason Statham"
        }




## Supplier Change [/supplier/{id}]

### Update an Existing Supplier [PUT]

You may update an existing supplier using this action. It takes a JSON
object containing the following properties.
###Omitted properties will be converted to null if not mandatory.  

+ name (string - mandatory) - The supplier's name.

   + Request (application/json)

            {
             "name": "Jason Statham",
            }

   + Response 201 (body)

            {
                "id": 15,
                "name": "Jason Statham"
            }
            


### Patch an Existing Supplier [PATCH]

You may patch an existing supplier by using this action. It takes a JSON
object containing the following properties. Omitted properties will not be changed. 
  
+ name (string) - The supplier's name.

   + Request (application/json)

            {
                "name": "Soups",
            }

   + Response 201 (body)

            {
                "id": 15,
                "name": "Soups"
            }



## Purchase Order [/purchaseOrder/{id}]

### Get a purchase order [GET]

+ Response 200 (application/json)
    
       {
                   "id": 2,
                   "supplier_id": 1,
                   "purchase_order_item": [
                       {
                           "id": 1,
                           "product_id": 1,
                           "quantity": 100,
                           "price": 1.00
                       },
                       {
                           "id": 2,
                           "product_id": 2,
                           "quantity": 200,
                           "price": 3.00
                       }
                   ]
               }
        

## Purchase Order Creation [/purchaseOrder/]

### Create a New Purchase Order [POST]

You may create a new purchase order using this action. It takes a JSON
object containing the following properties.
#####Omitted properties will be converted to null if not mandatory.  

+ supplier_id (number - mandatory) - The id for the supplier.
+ purchase_order_item (JSON object - mandatory) - The purchase order item to be created.
It consists of a product id (number - mandatory), quantity (number), price (number). 


+ Request (application/json)

            {"supplier_id":1,
                "purchase_order_item":[
                    {
                    "product_id":1,
                    "quantity": 100,
                    "price": 1.00
                }
                ]
            }
        
+ Response (body)

        {
            "id": 2,
            "supplier_id": 1,
            "purchase_order_item": [
                {
                    "id": 1,
                    "product_id": 1,
                    "quantity": 100,
                    "price": 1.00
                }
            ]
        }

## Purchase Order Change [/purchaseOrder/]

### Update an existing Purchase Order [PUT]

You may add a purchase order item to an existing purchase order using this action. It takes a JSON
object containing the following properties.

+ supplier_id (number - mandatory) - The id for the supplier.
+ purchase_order_item (JSON object - mandatory) - The purchase order item to be created.
It consists of a product id (number - mandatory), quantity (number), price (number). 


+ Request (application/json)

            {"supplier_id":1,
                "purchase_order_item":[
                {
                    "product_id":2,
                    "quantity": 200,
                    "price": 3.00
                }
                ]
            }
        
+ Response (body)

        {
            "id": 2,
            "supplier_id": 1,
            "purchase_order_item": [
                {
                    "id": 1,
                    "product_id": 1,
                    "quantity": 100,
                    "price": 1.00
                },
                {
                    "id": 2,
                    "product_id": 2,
                    "quantity": 200,
                    "price": 3.00
                }
            ]
        }


## Purchase Order Change [/purchaseOrder/{id}]

### Update an existing Purchase Order [PUT]

You may add a purchase order item to an existing purchase order using this action. It takes a JSON
object containing the following properties.

+ product_id (number - mandatory) - The product's id.
+ quantity (number) - The quantity for this purchase order item.
+ price (number) - The purchase price of the purchase order item.


   + Request (application/json)

           {
                "product_id":2,
                "quantity":200,
                "price": 3.00
               
           }

   + Response 201 (body)

            {
                "id": 2,
                "supplier_id": 1,
                "purchase_order_item": [
                {
                    "id": 1,
                    "product_id": 1,
                    "quantity": 100,
                    "price": 1.00
                },
                {
                    "id": 2,
                    "product_id": 2,
                    "quantity": 200,
                    "price": 3.00
                }
                ]
            }
            


