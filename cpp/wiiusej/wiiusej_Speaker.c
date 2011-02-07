#include "wiiusej_WiiUseApi.h"
#include "speaker.h"
#include "wiiuse.h"

static wiimote** wiimotes;
static int nbMaxWiimotes;

/*
void test(struct wiimote_t* wm) {
	int i;
	byte data[20] = {
		0xC3,0xC3,0xC3,0xC3,0xC3,0xC3,0xC3,0xC3,0xC3,0xC3,
		0xC3,0xC3,0xC3,0xC3,0xC3,0xC3,0xC3,0xC3,0xC3,0xC3};
	wiiuse_speaker_activate(wm);
	for (i = 0; i < 50; ++i) {
		wiiuse_speaker_data(wm, data);
	}
	wiiuse_speaker_deactivate(wm);
}*/

JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_activateSpeaker
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_speaker_activate(wiiuse_get_by_id(wiimotes, nbMaxWiimotes, id));
}

JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_deactivateSpeaker
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_speaker_deactivate(wiiuse_get_by_id(wiimotes, nbMaxWiimotes, id));
}
/*
void testen(struct wiimote_t* wm, unsigned short freq) {
	if (freq > 0x0000) {
		cfg[2] = freq & 0x00ff;
		cfg[3] = (freq & 0xff00) >> 8;
	}
	printf("unaangepast %d\n", freq);
	printf("speaker 0x%02x%02x\n", cfg[2], cfg[3]);
	fflush(stdout);
	wiiuse_write_data(wm, WM_REG_SPEAKER, cfg, 9);
}*/


JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setSpeakerFrequency
(JNIEnv *env, jobject obj, jint id, jint freq) {
	wiiuse_speaker_frequency(wiiuse_get_by_id(wiimotes, nbMaxWiimotes, id), (unsigned short) freq);
	//short a = ((short) freq) & 0xffff;
	//printf("jni %d %d\n", freq, a);
	//fflush(stdout);
	//testen(wiiuse_get_by_id(wiimotes, nbMaxWiimotes, id), (unsigned short) freq);
}

JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setSpeakerVolume
(JNIEnv *env, jobject obj, jint id, jshort vol) {
	wiiuse_speaker_frequency(wiiuse_get_by_id(wiimotes, nbMaxWiimotes, id), vol);
}


JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_streamSpeakerData
(JNIEnv *env, jobject obj, jint id, jshortArray sArray) {
	int i = 0;
	jshort *jShorts = (*env)->GetShortArrayElements(env, sArray, JNI_FALSE);
	byte data[sizeof(jShorts)];
	for (i = 0; i < sizeof(jShorts); ++i) {
		data[i] = (byte) jShorts[i];
	}
	wiiuse_speaker_data(wiiuse_get_by_id(wiimotes, nbMaxWiimotes, id), data);
	(*env)->ReleaseShortArrayElements(env, sArray, jShorts, JNI_FALSE);
}