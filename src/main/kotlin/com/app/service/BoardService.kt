package com.app.service

import com.app.data.BoardInfo

interface BoardService {
    fun selectBoardList(): List<BoardInfo>

    fun selectBoard(params: BoardInfo): List<BoardInfo>

    fun insertBoard(params: BoardInfo): String

    fun updateBoard(params: BoardInfo): String

    fun deleteBoard(params: BoardInfo): String

    fun deleteBoard01(params: BoardInfo): String

    fun updateViewCnt(params: BoardInfo): String


}