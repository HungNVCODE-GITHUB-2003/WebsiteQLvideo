package com.poly.implement;

import java.util.ArrayList;


import java.util.List;

import com.poly.dao.AbstractDao;
import com.poly.dao.StatsDao;
import com.poly.dto.UserLikeInfo;
import com.poly.dto.VideoLikedInfo;

public class StatsDaoImpl extends AbstractDao<Object[]> implements StatsDao {

	@Override
	public List<VideoLikedInfo> findVideoLikedInfo() {
		String sql = "select v.id, v.title, v.href, sum(cast(h.isLiked as int)) as totalLike from	video \r\n"
				+ "v left join history h on v.id = h.videoId \r\n"
				+ "group by v.id, v.title, v.href \r\n"
				+ "order by sum(cast(h.isLiked as int)) desc";
		List<Object[]> objects = super.findManyByNativeQuery(sql);
		List<VideoLikedInfo> result = new ArrayList<>();

		objects.forEach(object -> {
			VideoLikedInfo videoLikedInfo = setDataVideoLikedInfo(object);
			result.add(videoLikedInfo);
		});
		return result;
	}

	private VideoLikedInfo setDataVideoLikedInfo(Object[] object) {
		VideoLikedInfo videoLikedInfo = new VideoLikedInfo();
		videoLikedInfo.setVideoId((Integer) object[0]);
		videoLikedInfo.setTitle((String) object[1]);
		videoLikedInfo.setHref((String) object[2]);
		videoLikedInfo.setTotalLike(object[3] == null ? 0 : (Integer) object[3]);
		return videoLikedInfo;
	}

//	@Override
//	public List<UserLikeInfo> findUserLikeInfo1() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<UserLikeInfo> findUserLikeInfo() {
//		String sql = "select u.id , u.username , u.[password] , u.email , u.isAdmin , u.isActive ,sum(cast(h.isLiked as int)) as totalLike\r\n"
//				+ "from [user] u left join video v on u.id = v.id left join history h on v.id = h.videoId"
//				+ "group by u.id , u.username , u.[password] , u.email , u.isAdmin , u.isActive"
//				+ "order by sum(cast(h.isLiked as int)) desc";
//		
//		List<Object[]> objects = super.findManyByNativeQuery(sql);
//		List<UserLikeInfo> result = new ArrayList<>();
//		objects.forEach(object -> {
//			UserLikeInfo userLikeInfo = setDataUserLikeInfo(object);
//			result.add(userLikeInfo);
//		});
//		return result;
//	}
//	
//	private UserLikeInfo setDataUserLikeInfo(Object[] object) {
//		UserLikeInfo userLikeInfo = new UserLikeInfo();
//		userLikeInfo.set((Integer) object[0]);
//		UserLikeInfo.setTitle((String) object[1]);
//		UserLikeInfo.setHref((String) object[2]);
//		UserLikeInfo.setTotalLike(object[3] == null ? 0 : (Integer) object[3]);
//		return videoLikedInfo;
//	}
}
