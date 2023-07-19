package com.poly.dao;

import java.util.List;

import com.poly.dto.UserLikeInfo;
import com.poly.dto.VideoLikedInfo;
import com.poly.entity.User;

public interface StatsDao {

	List<VideoLikedInfo> findVideoLikedInfo();
	
//	List<UserLikeInfo> findUserLikeInfo();
}
