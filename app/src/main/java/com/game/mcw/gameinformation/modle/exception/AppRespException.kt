package com.game.mcw.gameinformation.modle.exception

import com.game.mcw.gameinformation.modle.AppResponse

class AppRespException : Exception {
    var mStatusCode: Int
    override var message: String

    constructor(response: AppResponse<*>) {
        mStatusCode = response.code.toInt()
        message = response.msg
    }
}