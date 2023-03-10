/*
 * Copyright (C) 2020 ByteDance Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#ifndef LOGGER_H
#define LOGGER_H

#include <android/log.h>
/***************************************************************************************************/

#ifdef __cplusplus
extern "C" {
#endif

#define LOGGER(fmt, ...) __android_log_print(ANDROID_LOG_ERROR, "TAILOR", fmt, ##__VA_ARGS__)

#ifdef __cplusplus
}
#endif

/***************************************************************************************************/
#endif //LOGGER_H