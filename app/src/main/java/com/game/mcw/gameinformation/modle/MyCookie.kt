package com.game.mcw.gameinformation.modle

import org.litepal.crud.LitePalSupport


class MyCookie(val type: Int, val url: String, val cookie: String) : LitePalSupport()
