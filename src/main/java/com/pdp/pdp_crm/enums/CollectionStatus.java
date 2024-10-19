package com.pdp.pdp_crm.enums;

public enum CollectionStatus {
    NEW,        // bu yaratilgan collection, lekin hali muddati kelmagan
    LATE,       // kech qolgan to'lov
    COMPLETED,  // to'langan to'lov
    PROCESSING  // to'lov qilinishi haqida ogohlantirish berilgan vaqti o'tmagan kech qolmagan
}
