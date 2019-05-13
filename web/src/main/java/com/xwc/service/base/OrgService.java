package com.xwc.service.base;

import com.xwc.commons.expception.BusinessException;
import com.xwc.commons.expception.DataException;
import com.xwc.controller.base.dto.org.OrgDto;
import com.xwc.controller.base.dto.org.OrgFilterDto;
import com.xwc.entity.base.Org;
import com.xwc.mapper.OrgMapper;
import com.xwc.service.auth.AuthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/26  22:03
 * 业务：
 * 功能：
 */
@Service
public class OrgService {

    @Autowired
    private AuthInfoService authInfoService;

    @Autowired
    private OrgMapper orgMapper;

    @Transactional
    public void add(OrgDto orgDto) {
        Org org = Org.convert(orgDto);
        Org parent = orgMapper.selectKey(org.getParentCode());
        if (parent == null) throw new BusinessException("上级机构不存在", org.getParentCode());
        String code = authInfoService.org().getCode();
        if (!org.getParentCode().startsWith(code)) throw new BusinessException("权限受限", code);
        org.setCode(GenderOrgCode(parent));
        org.setParentName(parent.getName());
        orgMapper.insert(org);
    }

    public void update(Org org) {
        get(org.getCode());
        Org parent = orgMapper.selectKey(org.getParentCode());
        if (parent == null) throw new BusinessException("上级机构不存在", org.getParentCode());
        String code = authInfoService.org().getCode();
        if (!org.getParentCode().startsWith(code)) throw new BusinessException("权限受限", code);
        org.setCode(GenderOrgCode(parent));
        org.setParentName(parent.getName());
        orgMapper.update(org);
    }

    public Org get(String code) {
        Org org = orgMapper.selectKey(code);
        if (org == null) throw new BusinessException("机构不存在", code);
        return org;
    }


    private String GenderOrgCode(Org org) {
        org.setSons(org.getSons() + 1);
        orgMapper.update(org);
        StringBuilder s = new StringBuilder(Integer.toString(org.getSons(), 32));
        if (s.length() > 3) throw new DataException("机构层级超过上限", org.getCode() + "机构下的子机构数量超过上限");
        while (s.length() < 3) {
            s.insert(0, "0");
        }
        s.insert(0, org.getCode());
        if (s.length() > 32) {
            throw new BusinessException("机构层级超过上限", org.getCode());
        }
        return s.toString();

    }


    public List<Org> list(OrgFilterDto filter) {
        return orgMapper.list(filter);
    }

    public Long count(OrgFilterDto filterDto) {
        return orgMapper.count(filterDto);
    }


    public List<Org> getParentCode(String code) {
        return orgMapper.listByLikeParent(code);
    }
}
