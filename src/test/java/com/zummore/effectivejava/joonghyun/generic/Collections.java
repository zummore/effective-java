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
package com.zummore.effectivejava.joonghyun.generic;

import java.util.List;

public class Collections {

    /**
     * PECS : producer-extends, consumer-super
     *
     * @param dest 소비자 consumer-super
     * @param src  생산자 producer-extends
     * */
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++) {
            dest.add(src.get(i));
        }
    }

    /**
     * 소비자에 super 사용하지 않아도 컴파일 성공
     * */
    public static <T> void copy2(List<T> dest, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++)
            dest.add(src.get(i));
    }

    /**
     * 소비자에 extends 사용하였을경우 컴파일 에러
     * */
    public static <T> void copy3(List<? extends T> dest, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++) {
            //dest.add(src.get(i)); //컴파일에러
        }
    }
}
