package com.msunsoft.spt.business.service.user.impl;

import com.msunsoft.spt.business.domain.user.TbPermission;
import com.msunsoft.spt.business.domain.user.TbRolePermission;
import com.msunsoft.spt.business.mapper.user.TbPermissionMapper;
import com.msunsoft.spt.business.mapper.user.TbRolePermissionMapper;
import com.msunsoft.spt.business.service.common.impl.BaseCrudServiceImpl;
import com.msunsoft.spt.business.service.user.TbPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zxl
 * @create 2020/4/9 0009
 */
@Service
public class TbPermissionServiceImpl extends BaseCrudServiceImpl<TbPermission, TbPermissionMapper> implements TbPermissionService {

    @Autowired
    private TbPermissionMapper tbPermissionMapper;
    @Autowired
    private TbRolePermissionMapper tbRolePermissionMapper;

    @Override
    public List<TbPermission> selectByUserId(Long userId) {
        return tbPermissionMapper.selectByUserId(userId);
    }

    @Transactional
    @Override
    public int permissionDelete(long id) {
        int i = 0;
        TbRolePermission tbRolePermission = new TbRolePermission();
        tbRolePermission.setPermissionId(id);
        try {
            tbPermissionMapper.deleteByPrimaryKey(id);
            tbRolePermissionMapper.delete(tbRolePermission);
            i=1;
        }catch (Exception e){
            return 0;
        }

        return i;
    }



    @Override
    public List<Map<String, Object>> loadPermissionList(long roleId) {

        long parentId = 0;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> childrenList = getChildeList(roleId, parentId);
        map.put("id", "0");
        map.put("label", "资源");
        map.put("enname", "ziyuan");
        map.put("children", childrenList);
        list.add(map);
        return list;

    }

    public List<Map<String, Object>> getChildeList(long roleId, long perent_id) {
        long parentId = perent_id;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        List<Map<String, Object>> childrenList = selectResouceByParams(roleId, parentId);
        for (Map<String, Object> m : childrenList) {
            long id = (long) m.get("id");
            List<Map<String, Object>> list3 = getChildeList(roleId, id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            map.put("label", m.get("name"));
            map.put("enname", m.get("enname"));
            map.put("checked", Boolean.parseBoolean(m.get("checked").toString()));
            map.put("children", list3);
            if (list3 == null || list3.size() == 0) {
                map.put("checked", Boolean.parseBoolean(m.get("checked").toString()));
            }
            list.add(map);
        }

        return list;
    }
    public List<Map<String, Object>> selectResouceByParams(long role_id, long id) {

        List<Map<String, Object>> list = tbPermissionMapper.selectPermissionByParams(role_id, id);
        // 空数据的话 直接返回
        if (list == null || list.size() == 0) {
            return new ArrayList<Map<String, Object>>();
        }

        return list;
    }
}
