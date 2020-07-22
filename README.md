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

