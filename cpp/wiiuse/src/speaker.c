#include <stdio.h>
#include "definitions.h"
#include "wiiuse_internal.h"
#include "wiiuse.h"
#include "speaker.h"

void wiiuse_speaker_enable(struct wiimote_t* wm) {
	byte buf = WIIMOTE_GET_RUMBLE(wm) | WM_CTRL_STATUS_BYTE1_SPEAKER_ENABLED;
	wiiuse_send(wm, WM_CMD_SPEAKER_ENABLE, &buf, 1);
}

void wiiuse_speaker_disable(struct wiimote_t* wm) {
	byte buf = WIIMOTE_GET_RUMBLE(wm);
	wiiuse_send(wm, WM_CMD_SPEAKER_ENABLE, &buf, 1);
}

void wiiuse_speaker_mute(struct wiimote_t* wm) {
	byte buf = WIIMOTE_GET_RUMBLE(wm) | WM_CTRL_STATUS_BYTE1_SPEAKER_MUTE;
	wiiuse_send(wm, WM_CMD_SPEAKER_MUTE, &buf , 1);
}

void wiiuse_speaker_unmute(struct wiimote_t* wm) {
	byte buf = WIIMOTE_GET_RUMBLE(wm);
	wiiuse_send(wm, WM_CMD_SPEAKER_MUTE, &buf, 1);
}

void wiiuse_speaker_activate(struct wiimote_t* wm) {
	wiiuse_speaker_enable(wm);
	wiiuse_speaker_mute(wm);
	byte buf = 0x01;
	wiiuse_write_data(wm, WM_REG_SPEAKER + 8, &buf, 1);
	buf = 0x08;
	wiiuse_write_data(wm, WM_REG_SPEAKER, &buf, 1);
	wiiuse_speaker_config(wm);
	buf = 0x01;
	wiiuse_write_data(wm, WM_REG_SPEAKER + 7, &buf, 1);
	wiiuse_speaker_unmute(wm);
}

void wiiuse_speaker_deactivate(struct wiimote_t* wm) {
	wiiuse_speaker_mute(wm);
	wiiuse_speaker_disable(wm);
}

void wiiuse_speaker_format(struct wiimote_t* wm, byte format) {
	wm->speaker.format = format;
}

void wiiuse_speaker_rate(struct wiimote_t* wm, double rate) {
	// Check: pcm_sample_rate = 12000000 / rate_value adpcm_sample_rate = 6000000 / rate_value
	wm->speaker.rate = 48000 / rate;
}

void wiiuse_speaker_volume(struct wiimote_t* wm, double vol) {
	wm->speaker.vol = vol * WIIMOTE_GET_SPEAKER_MAX_VOLUME(wm);
}

void wiiuse_speaker_config(struct wiimote_t* wm) {
	byte cfg[7] = {wm->speaker.format, 0x00, 0x00, wm->speaker.rate, wm->speaker.vol, 0x00, 0x00};
	wiiuse_write_data(wm, WM_REG_SPEAKER, cfg, 7);
}

void wiiuse_speaker_data(struct wiimote_t* wm, byte* data) {
	/* Todo: add data length dynamically */
	//byte buf[21];
	//WIIUSE_DEBUG("data length %d", sizeof(buf) / sizeof(byte));
	//memcpy(buf, data, 21);
	wiiuse_send(wm, WM_CMD_STREAM_DATA, data, 21);
}
