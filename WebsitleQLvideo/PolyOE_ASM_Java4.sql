use master
go

drop DATABASE if exists [PolyOE_ASM_Java4]
go

create database [PolyOE_ASM_Java4]
go

use  PolyOE_ASM_Java4
go

create table [user]
(
	id				int				primary key identity,
	username		varchar(20)		unique not null,
	[password]		varchar(20)		not null,
	email			varchar(50)		unique not null,
	isAdmin			bit				not null default 0,
	isActive		bit				not null default 1
)
go

create table video
(
	id				int				primary key identity,
	title			varchar(255)	not null,
	href			varchar(50)		unique not null,
	poster			varchar(255)	null,
	[views]			int				not null default 0,
	shares			int				not null default 0,
	[description]	nvarchar(255)	not null,
	isActive		bit				not null default 1
)
go

create table history
(
	id				int				primary key identity,
	userId			int				foreign key references [user] (id),
	videoId			int				foreign key references video (id),
	viewedDate		datetime		not null default getDate(),
	isLiked			bit				not null default 0,
	likeDate		datetime		null
)
go

insert into [user] (username, [password], email, isAdmin) values
('HungSmall',		'hungnho',		'hungnvps@fpt.edu.vn',		1),
('Nghiaht',		'123',		'nghiahtps@fpt.edu.vn',	1),
('yasou',		'boss',		'hungyasou2003@gmail.com',	0),
('Phong',		'123',		'phongvtps2003@gmail.com',	0)
go

insert into video (title, href ,poster, [description]) values
(N'I dream in another language',	'6gic246xNEQ', 'https://img.youtube.com/vi/6gic246xNEQ/maxresdefault.jpg',	N'Thật không may cho anh ta, những người đàn ông đang có mối thù và không nói chuyện với nhau trong 50 năm..'),

(N'Whitney','uRu3zLOJN2c','https://img.youtube.com/vi/uRu3zLOJN2c/maxresdefault.jpg',	N'Lucas Hood, một cựu kẻ lừa đảo bí ẩn, giả định danh tính của một cảnh sát trưởng bị sát hại.'),

(N'Blindspotting','-9-HBqVbtTo','https://img.youtube.com/vi/-9-HBqVbtTo/maxresdefault.jpg',	N'Mọi chuyện sớm trở nên căng thẳng khi những người bạn tham dự một bữa tiệc tại ngôi nhà cao cấp của một doanh nhân công nghệ trẻ và giàu có.'),
(N'John wick4', 'yjRHZEUamCc',	'https://img.youtube.com/vi/yjRHZEUamCc/maxresdefault.jpg',	N'Với cái giá phải trả cho cái đầu ngày càng tăng, sát thủ huyền thoại John Wick tham gia cuộc chiến chống lại High Table toàn cầu khi anh ta tìm kiếm những tay chơi mạnh nhất trong thế giới ngầm.'),
(N'THE WORLD WITHOUT OUTSIDE', 'N5M54oIHNmo'	,'https://img.youtube.com/vi/N5M54oIHNmo/maxresdefault.jpg',	N'Thế giới không lối thoát là bộ phim truyền hình trực tuyến chính kịch, kịch tích và khoa học viễn tưởng của Nhật Bản ra mắt năm 2020 dựa trên bộ truyện manga cùng tên của tác giả Haro Aso.'),
(N'HUNG LONG PHONG BA', 'Tai_Oa0kwAg',	'https://img.youtube.com/vi/Tai_Oa0kwAg/maxresdefault.jpg',	N'PHIM HÀNH ĐỘNG SỐ 1 VN | PHIM HÀNH ĐỘNG KỂ VỀ CUỘC ĐỜI CỦA HÙNG LONG PHONG VÀ BÁ.'),
(N'NAOH', 'fdu10cX3pWA',	'https://img.youtube.com/vi/fdu10cX3pWA/maxresdefault.jpg',	N'God decides to wash away the sins of mankind through an apocalyptic flood. Before that happens, Noah is tasked with building an ark that can carry his family and a breeding pair of all animals.'),
(N'kill bok soon', 'FFryHV_WYB8',	'https://img.youtube.com/vi/FFryHV_WYB8/maxresdefault.jpg',	N'God decides to wash away the sins of mankind through an apocalyptic flood. Before that happens, Noah is tasked with building an ark that can carry his family and a breeding pair of all animals.'),
(N'vincenzo', 'S12-4mXCNj4',	'https://img.youtube.com/vi/S12-4mXCNj4/maxresdefault.jpg',	N'During a visit to his motherland, a Korean-Italian mafia lawyer gives a conglomerate a taste of its own medicine with a side of justice.')
go

insert into history (userId, videoId, isLiked, likeDate) values
(2,	1,	1,	getDate()),
(2,	3,	0,	null)
go

select * from [user]
select * from video
select * from history





select v.id, v.title, v.href, sum(cast(h.isLiked as int)) as totalLike from	video 
v left join history h on v.id = h.videoId 
group by v.id, v.title, v.href 
order by sum(cast(h.isLiked as int)) desc
go

create proc sp_selectUserLikedVideoByVideoHref(@videoHref varchar(50))
as begin
	select
		u.id , u.username, u.[password] ,u.email,u.isAdmin,u.isActive
	from
		video v left join history h on v.id = h.videoId
			left join [user] u on h.userId = u.id
	where
		v.href = @videoHref and u.isActive = 1 and v.isActive = 1 and h.isLiked = 1
end

	

