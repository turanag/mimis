#include <stdio.h>
#include "definitions.h"
#include "wiiuse_internal.h"
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

void wiiuse_speaker_config(struct wiimote_t* wm, unsigned short freq, byte vol) {
	if (freq > 0x0000) {
		cfg[2] = freq & 0x00ff;
		cfg[3] = (freq & 0xff00) >> 8;
	}
	printf("speaker 0x%02x%02x\n", cfg[2], cfg[3]);
	fflush(stdout);
	if (vol > 0x00) {
		cfg[4] = vol;
	}
	wiiuse_write_data(wm, WM_REG_SPEAKER, cfg, 9);
}

void wiiuse_speaker_data(struct wiimote_t* wm, byte* data) {
	byte buf[21];
	buf[0] = sizeof(data) << 3;
	memcpy(buf + 1, data, 20);
	wiiuse_send(wm, WM_CMD_STREAM_DATA, buf, 21);
}

void wiiuse_speaker_activate(struct wiimote_t* wm) {
	wiiuse_speaker_enable(wm);
	wiiuse_speaker_unmute(wm);
	wiiuse_speaker_config(wm, 0x00, 0x00);
}

void wiiuse_speaker_deactivate(struct wiimote_t* wm) {
	wiiuse_speaker_mute(wm);
	wiiuse_speaker_disable(wm);
}

void wiiuse_speaker_frequency(struct wiimote_t* wm, unsigned short freq) {
	wiiuse_speaker_config(wm, freq, 0x00);
}

void wiiuse_speaker_volume(struct wiimote_t* wm, byte vol) {
	wiiuse_speaker_config(wm, 0x0000, vol);
}