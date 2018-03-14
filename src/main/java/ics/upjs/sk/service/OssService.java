package ics.upjs.sk.service;

import ics.upjs.sk.core.scs.SlovoSCS;
import ics.upjs.sk.core.synonyma.Synonymum;
import ics.upjs.sk.dao.ScsDao;
import ics.upjs.sk.dao.SsjDao;
import ics.upjs.sk.dao.SynonymumDao;
import ics.upjs.sk.dto.ScsDto;
import ics.upjs.sk.dto.SsjDto;
import ics.upjs.sk.dto.SynonymumDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raven
 */
public class OssService {
    
    private final  ScsDao scsDao;
    private final SsjDao ssjDao;
    private final SynonymumDao synonymumDao;
    
    public OssService(ScsDao scsDao, SsjDao ssjDao, SynonymumDao synonymumDao) {
        this.scsDao = scsDao;
        this.ssjDao = ssjDao;
        this.synonymumDao = synonymumDao;
    }
    
    public List<ScsDto> vratVsetkySlovaSCS() {
        return new ScsDto().vratDTOSlovaSCS(scsDao.findAll());
    }
    
    public List<SsjDto> vratVsetkySlovaSSJ() {
        return new SsjDto().vratDTOSlovaSSJ(ssjDao.findAll());
    }
    
    
}
