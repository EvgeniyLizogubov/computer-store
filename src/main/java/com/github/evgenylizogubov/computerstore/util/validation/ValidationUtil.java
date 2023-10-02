package com.github.evgenylizogubov.computerstore.util.validation;

import com.github.evgenylizogubov.computerstore.error.IllegalRequestDataException;
import com.github.evgenylizogubov.computerstore.model.BaseEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {
    public static void checkNew(BaseEntity bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }
    
    public static void assureIdConsistent(BaseEntity bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must have id=" + id);
        }
    }
}
