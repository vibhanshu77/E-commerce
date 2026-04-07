package com.MSCA.Ecommerce.enums;

public enum AccountStatus {

    INACTIVE, // temporarily locked -> means can see order history but not able to place order.
    LOCKED,
    ACTIVE,
    CLOSED, //Employee is no more in the organization (Resign or terminated)
    PENDING, // for newly joined user
    SUSPEND // for customer
}
