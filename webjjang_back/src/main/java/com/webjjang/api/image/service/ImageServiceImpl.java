package com.webjjang.api.image.service;

import com.querydsl.core.Tuple;
import com.webjjang.api.image.entity.Image;
import com.webjjang.api.image.repository.ImageRepositoryCustom;
import com.webjjang.api.image.repository.QImageRepository;
import com.webjjang.api.image.vo.ImageVO;
import com.webjjang.api.util.page.PageObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepositoryCustom imageRepositoryCustom;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private QImageRepository qImageRepository;

    @Override
    public List<ImageVO> list(PageObject pageObject) {

        // 화면에 페이지 내이션을 표시하기 위한 페이지
        pageObject.setTotalRow(imageRepositoryCustom.getCount(pageObject.getKey(), pageObject.getWord()));

        List<Tuple> tupleList = imageRepositoryCustom.getList(
                pageObject.getPage(),
                pageObject.getPerPageNum(),
                pageObject.getKey(),
                pageObject.getWord()
        );

        // List<Tuple> to List<ImageVO>
        List<ImageVO> list = new ArrayList<>();
        for(Tuple tuple : tupleList){
            ImageVO vo = new ImageVO();
            vo.setNo(tuple.get(0, Long.class));
            vo.setTitle(tuple.get(1, String.class));
            vo.setWriter(tuple.get(2, String.class));
            vo.setHit(tuple.get(3, Long.class));
            vo.setWriteDate(tuple.get(4, LocalDateTime.class));
            list.add(vo);
        }

        return list;
    }

    @Override
    public ImageVO view(Long no, Integer inc) {
        if(inc == 1) imageRepositoryCustom.increaseHit(no);
        Tuple tuple = imageRepositoryCustom.getImage(no);
        ImageVO vo = new ImageVO();
        vo.setNo(tuple.get(0, Long.class));
        vo.setTitle(tuple.get(1, String.class));
        vo.setContent(tuple.get(2, String.class));
        vo.setWriter(tuple.get(3, String.class));
        vo.setHit(tuple.get(4, Long.class));
        vo.setWriteDate(tuple.get(5, LocalDateTime.class));
        return vo;
    }

    // Image -> ImageVO
    ImageVO imageToImageVO(Image image){
        ImageVO vo = new ImageVO();
        vo.setNo(image.getNo());
        vo.setTitle(image.getTitle());
        vo.setContent(image.getContent());
        vo.setWriter(image.getWriter());
        vo.setHit(image.getHit());
        vo.setWriteDate(image.getWriteDate());
        vo.setUpdateDate(image.getUpdateDate());
        vo.setPw(image.getPw());
        return vo;
    }

    // ImageVO -> Image
    Image imageVOToImage(ImageVO vo){
        Image image = new Image();
        image.setNo(vo.getNo());
        image.setTitle(vo.getTitle());
        image.setContent(vo.getContent());
        image.setWriter(vo.getWriter());
        image.setWriteDate(vo.getWriteDate());
        image.setUpdateDate(vo.getUpdateDate());
        image.setPw(passwordEncoder.encode(vo.getPw()));
        return image;
    }

    @Override
    @Transactional
    public ImageVO write(ImageVO vo) {
        Image image = imageRepositoryCustom.writeImage(
                imageVOToImage(vo) // ImageVO -> Image
        );

        return imageToImageVO(image); // Image -> ImageVO
    }

    @Override
    @Transactional
    public Long update(ImageVO vo) {
        log.info("[update] imageVOToImage(vo) = {}", imageVOToImage(vo));
        // pw를 포함한 데이터를 먼저 가져온다.
        Optional<Image> optional = qImageRepository.findById(vo.getNo());
        if (optional.isEmpty()) throw new RuntimeException("일반게시판 수정 오류 - 글 번호 확인");
        Image image = optional.get();
        if (passwordEncoder.matches(vo.getPw(), image.getPw()))
            throw new RuntimeException("일반게시판 수정 오류 - 비밀번호 확인");
        // 수정할 데이터로 변경 : image
        image.setTitle(vo.getTitle());
        image.setContent(vo.getContent());
        image.setWriter(vo.getWriter());

        // save(image) : 데이터가 있으면 수정(update)을 없으면 등록(insert)을 시킨다.
        Image updatedImage = imageRepositoryCustom.updateImage(image);

        return 1L;
    }

    @Override
    @Transactional
    public Long delete(ImageVO vo) {

        // pw를 포함한 데이터를 먼저 가져온다.
        Optional<Image> optional = qImageRepository.findById(vo.getNo());
        if (optional.isEmpty()) throw new RuntimeException("일반게시판 삭제 오류 - 글 번호 확인");
        Image image = optional.get();
        if (passwordEncoder.matches(vo.getPw(), image.getPw()))
            throw new RuntimeException("일반게시판 삭제 오류 - 비밀번호 확인");
        imageRepositoryCustom.deleteImage(vo.getNo());

        return 1L;
    }
}
