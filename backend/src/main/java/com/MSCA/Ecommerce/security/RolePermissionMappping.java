package com.MSCA.Ecommerce.security;

import com.MSCA.Ecommerce.enums.Permission;
import com.MSCA.Ecommerce.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.MSCA.Ecommerce.enums.Permission.*;
import static com.MSCA.Ecommerce.enums.Role.*;

public class RolePermissionMappping {

    Map<Role, Set<Permission>> map = Map.of(

            ROLE_WAREHOUSE_ADMIN, Set.of(USER_MANAGE,
                                        ROLE_ASSIGN,
                                        WAREHOUSE_CONFIGURE,
                                        AUDIT_VIEW,
                                        SYSTEM_OVERRIDE),

            ROLE_WAREHOUSE_MANAGER, Set.of(WAREHOUSE_CREATE,
                                            WAREHOUSE_EDIT,
                                            ZONE_CONFIGURE,
                                            MANAGER_ASSIGN,
                                            REPORT_VIEW_ALL),

            ROLE_WAREHOUSE_SUPERVISOR, Set.of(SHIPMENT_APPROVE,
                                                INVENTORY_ADJUST,
                                                STAFF_ASSIGN,
                                                REPORT_VIEW_MANAGER,
                                                EXCEPTION_RESOLVE),

            ROLE_WAREHOUSE_STAFF_HEAD, Set.of(TASK_ASSIGN_STAFF,
                                                TASK_MONITOR,
                                                ISSUE_ESCALATE,
                                                REPORT_VIEW_STAFFHEAD),

            ROLE_WAREHOUSE_STAFF, Set.of( TASK_EXECUTE_PICK,
                                            TASK_EXECUTE_PACK,
                                            BARCODE_SCAN,
                                            STOCK_UPDATE,
                                            ORDER_STATUS_UPDATE),

            ROLE_CUSTOMER, Set.of(ORDER_PLACE,
                                    ORDER_TRACK,
                                    ORDER_CANCEL,
                                    RETURN_REQUEST,
                                    PROFILE_UPDATE)
    );

    public Set<SimpleGrantedAuthority> getPermissions(Role role){

        return map.get(role).stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.name()))
                    .collect(Collectors.toSet());

    }

}
