package zzyzzy.poject.sungjuk.dao;

import zzyzzy.poject.sungjuk.model.SungJukVO;

import java.util.List;

public interface SungJukV3DAO {

    boolean saveSungJuk(SungJukVO sj);

    List<SungJukVO> loadSungJuk();

    void writeSungJuk(List<SungJukVO> sjs);
}
