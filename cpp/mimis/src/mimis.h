#ifndef MIMIS_H_
#define MIMIS_H_

#include <windows.h>
#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     mimis_util_Native
 * Method:    getHandle
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_mimis_util_Native_getHandle
  (JNIEnv *, jclass, jstring);

/*
 * Class:     mimis_util_Native
 * Method:    sendMessage
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_mimis_util_Native_sendMessage
  (JNIEnv *, jclass, jint, jint, jint, jint);

/*
 * Class:     mimis_util_Native
 * Method:    postMessage
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_mimis_util_Native_postMessage
  (JNIEnv *, jclass, jint, jint, jint, jint);

/*
 * Class:     mimis_util_Native
 * Method:    mapVirtualKey
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_mimis_util_Native_mapVirtualKey
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     mimis_util_Native
 * Method:    isRunning
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_mimis_util_Native_isRunning
  (JNIEnv *, jclass, jstring);

/*
 * Class:     mimis_util_Native
 * Method:    terminate
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_mimis_util_Native_terminate
  (JNIEnv *, jclass, jstring);
/*
 * Class:     mimis_util_Native
 * Method:    getValue
 * Signature: (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */

/*
 * Class:     mimis_util_Native
 * Method:    getValue
 * Signature: (Lmimis/value/Registry;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_mimis_util_Native_getValue
  (JNIEnv *, jclass, jint, jstring, jstring);

#ifdef __cplusplus
}
#endif
#endif
