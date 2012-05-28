/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class wiiusej_WiiUseApi */

#ifndef _Included_wiiusej_WiiUseApi
#define _Included_wiiusej_WiiUseApi
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     wiiusej_WiiUseApi
 * Method:    connect
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_wiiusej_WiiUseApi_connect
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    find
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_wiiusej_WiiUseApi_find
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    init
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_init
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    closeConnection
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_closeConnection
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    getUnId
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_wiiusej_WiiUseApi_getUnId
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    cleanUp
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_cleanUp
  (JNIEnv *, jobject);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    activateRumble
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_activateRumble
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    deactivateRumble
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_deactivateRumble
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    activateIRTracking
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_activateIRTracking
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    deactivateIRTracking
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_deactivateIRTracking
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    activateMotionSensing
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_activateMotionSensing
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    deactivateMotionSensing
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_deactivateMotionSensing
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    setLeds
 * Signature: (IZZZZ)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setLeds
  (JNIEnv *, jobject, jint, jboolean, jboolean, jboolean, jboolean);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    setOrientThreshold
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setOrientThreshold
  (JNIEnv *, jobject, jint, jfloat);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    setAccelThreshold
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setAccelThreshold
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    setAlphaSmoothing
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setAlphaSmoothing
  (JNIEnv *, jobject, jint, jfloat);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    reSync
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_reSync
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    activateSmoothing
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_activateSmoothing
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    deactivateSmoothing
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_deactivateSmoothing
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    activateContinuous
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_activateContinuous
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    deactivateContinuous
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_deactivateContinuous
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    setScreenRatio43
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setScreenRatio43
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    setScreenRatio169
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setScreenRatio169
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    setSensorBarAboveScreen
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setSensorBarAboveScreen
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    setSensorBarBelowScreen
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setSensorBarBelowScreen
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    setVirtualScreenResolution
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setVirtualScreenResolution
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    getStatus
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_getStatus
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    setTimeout
 * Signature: (ISS)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setTimeout
  (JNIEnv *, jobject, jint, jshort, jshort);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    setIrSensitivity
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setIrSensitivity
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    setNunchukOrientationThreshold
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setNunchukOrientationThreshold
  (JNIEnv *, jobject, jint, jfloat);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    setNunchukAccelerationThreshold
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setNunchukAccelerationThreshold
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    windowsSetBluetoothStack
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_windowsSetBluetoothStack
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    specialPoll
 * Signature: (Lwiiusej/wiiusejevents/utils/EventsGatherer;)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_specialPoll
  (JNIEnv *, jobject, jobject);

#ifdef __cplusplus
}
#endif
#endif
