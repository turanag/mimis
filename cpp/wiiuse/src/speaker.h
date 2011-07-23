#include "wiiuse.h"

#define WIIMOTE_GET_RUMBLE(wm) 				(WIIMOTE_IS_SET(wm, WIIMOTE_STATE_RUMBLE) ? 0x01 : 0x00)
#define WIIMOTE_GET_SPEAKER_MAX_VOLUME(wm)	(wm->speaker.format == 0x00 ? 0x40 : 0xff)

#define WM_CMD_SPEAKER_ENABLE 0x14
#define WM_CMD_STREAM_DATA    0x18
#define WM_CMD_SPEAKER_MUTE   0x19

#define WM_CTRL_STATUS_BYTE1_SPEAKER_MUTE 0x04

#define WM_REG_SPEAKER 0x04a20001
