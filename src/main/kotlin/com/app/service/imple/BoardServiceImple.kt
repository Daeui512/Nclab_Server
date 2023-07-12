package com.app.service.imple

import com.app.dao.BoardDao
import com.app.data.BoardInfo
import com.app.service.BoardService

class BoardServiceImple: BoardService {

    /**
     * 전체 게시물 조회
     */
    override fun selectBoardList(): List<BoardInfo> {
        return BoardDao.selectBoardList()
    }

    /**
     * 게시물 번호로 특정 게시물 조회
     */
    override fun selectBoard(params: BoardInfo): List<BoardInfo> {
        return BoardDao.selectBoard(params)
    }

    /**
     * 게시물 추가
     */
    override fun insertBoard(params: BoardInfo): String {
        return BoardDao.insertBoard(params)
    }

    /**
     * 게시물 수정
     */
    override fun updateBoard(params: BoardInfo): String {
        return BoardDao.updateBoard(params)
    }

    /**
     * 게시물 삭제
     */
    override fun deleteBoard(params: BoardInfo): String {
        return BoardDao.deleteBoard(params)
    }

    /**
     * 게시물 삭제(예비)
     */
    override fun deleteBoard01(params: BoardInfo): String {
        return BoardDao.deleteBoard01(params)
    }

    /**
     * 조회수 업데이트
     */
    override fun updateViewCnt(params: BoardInfo): String {
        return BoardDao.updateViewCnt(params)
    }
}