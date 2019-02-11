/* Copyright (c) 2019 ZUM Internet, Inc.
 * All right reserved.
 * http://www.zum.com
 * This software is the confidential and proprietary information of ZUM
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with ZUM.
 *
 * Revision History
 * Author                    Date                     Description
 * ------------------       --------------            ------------------
 *   joonghyun                2019-01-22
 */
package com.zummore.effectivejava.joonghyun.equals;

import java.util.Objects;

public class EqualsSuccessClazz {

    private String name;
    private Long age;

    public EqualsSuccessClazz(String name, Long age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EqualsSuccessClazz)) return false;
        EqualsSuccessClazz that = (EqualsSuccessClazz) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
