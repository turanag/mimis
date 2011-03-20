#include "wiiuse.h"

#define WIIMOTE_GET_RUMBLE(wm) (WIIMOTE_IS_SET(wm, WIIMOTE_STATE_RUMBLE) ? 0x01 : 0x00)

#define WM_CMD_SPEAKER_ENABLE 0x14
#define WM_CMD_STREAM_DATA    0x18
#define WM_CMD_SPEAKER_MUTE   0x19

#define WM_CTRL_STATUS_BYTE1_SPEAKER_MUTE 0x04

#define WM_REG_SPEAKER 0x04a20001

static byte cfg[9] = {0x00, 0x00, 0x00, 0xdd, 0x40, 0x00, 0x00, 0x01, 0x01};

WIIUSE_EXPORT extern void wiiuse_speaker_enable(struct wiimote_t* wm);
WIIUSE_EXPORT extern void wiiuse_speaker_disable(struct wiimote_t* wm);
WIIUSE_EXPORT extern void wiiuse_speaker_mute(struct wiimote_t* wm);
WIIUSE_EXPORT extern void wiiuse_speaker_unmute(struct wiimote_t* wm);
WIIUSE_EXPORT extern void wiiuse_speaker_activate(struct wiimote_t* wm);
WIIUSE_EXPORT extern void wiiuse_speaker_deactivate(struct wiimote_t* wm);
WIIUSE_EXPORT extern void wiiuse_speaker_volume(struct wiimote_t* wm, byte vol);
WIIUSE_EXPORT extern void wiiuse_speaker_frequency(struct wiimote_t* wm, unsigned short freq);
WIIUSE_EXPORT extern void wiiuse_speaker_data(struct wiimote_t* wm, byte* data);
