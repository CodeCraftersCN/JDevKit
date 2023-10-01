/*
 * Copyright (C) 2023 CodeCraftersCN.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.org.codecrafters.guid;

/**
 * {@link GuidCreator} 是一个生成指定类型的全局唯一 ID 的通用接口。
 * <p>
 * ID 类型由实现该接口的类决定。
 *
 * @param <IdType> 全局唯一 ID 的类型
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
public interface GuidCreator<IdType> {

    /**
     * 生成并返回下一个全局唯一 ID。
     * <p>
     * 如何生成和返回全局唯一 ID 的具体实现取决于实现此方法的类。
     *
     * @return 下一个全局唯一 ID
     */
    IdType nextId();

}