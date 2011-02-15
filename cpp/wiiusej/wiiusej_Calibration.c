#include "wiiusej_WiiUseApi.h"
#include "wiiuse.h"

static wiimote** wiimotes;
static int nbMaxWiimotes;


JNIEXPORT jshortArray JNICALL Java_wiiusej_WiiUseApi_getCalibration
(JNIEnv *env, jobject obj, jint id) {
	struct wiimote_t* wm = wiiuse_get_by_id(wiimotes, nbMaxWiimotes, id);
	const accel_t* accel = &wm->accel_calib;
	const vec3b_t* zero = &accel->cal_zero;
	const vec3b_t* g = &accel->cal_g;
	short calibration[] = {
		zero->x, zero->y, zero->z,
		g->x, g->y, g->z};
	int size = 6;
	jshortArray jShorts = (*env)->NewShortArray(env, size);
	(*env)->SetShortArrayRegion(env, jShorts, 0, size, calibration);
	return jShorts;
}