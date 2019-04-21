package cn.hselfweb.cnjpbbs.repository;

import cn.hselfweb.cnjpbbs.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


public interface BannerRepository extends JpaRepository<Banner,Long> {
}
