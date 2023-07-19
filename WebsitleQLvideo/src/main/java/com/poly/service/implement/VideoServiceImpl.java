package com.poly.service.implement;

import java.util.List;


import com.poly.dao.VideoDao;

import com.poly.entity.Video;
import com.poly.implement.VideoDaoImpl;
import com.poly.service.VideoService;

public class VideoServiceImpl implements VideoService {

	private VideoDao dao;

	public VideoServiceImpl() {
		dao = new VideoDaoImpl(); // interface ko lưu đc object lên phải dùng class
	}

	@Override
	public Video findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Video findByHref(String href) {
		return dao.findByHref(href);
	}

	@Override
	public List<Video> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Video> findAll(int pageNumber, int pageSize) {
		return dao.findAll(pageNumber, pageSize);
	}

	@Override
	public Video create(Video entity) {
		entity.setIsActive(Boolean.TRUE);
		entity.setViews(0);
		entity.setShares(0);
		return dao.create(entity);
	}

	@Override
	public Video update(Video entity) {
		return dao.update(entity);
	}

	@Override
	public Video detele(String href) {
		Video entity = findByHref(href);
		entity.setIsActive(Boolean.FALSE); // ko xóa khói database sẽ đổi trạng thái false
		return dao.update(entity);
	}

}
