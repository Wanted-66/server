package dev.changuii.project.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.changuii.project.dto.SearchUserDto;
import dev.changuii.project.dto.response.FriendListResponseDto;
import dev.changuii.project.dto.response.FriendRequestResponseDto;
import dev.changuii.project.entity.SampleEntity;
import dev.changuii.project.enums.FriendshipStatus;
import dev.changuii.project.repository.CustomSampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import static dev.changuii.project.entity.QFriendshipEntity.friendshipEntity;
import static dev.changuii.project.entity.QUserEntity.userEntity;

import java.util.List;

import static dev.changuii.project.entity.QSampleEntity.sampleEntity;


public class CustomSampleRepositoryImpl implements CustomSampleRepository {

    private JPAQueryFactory jpaQueryFactory;

    public CustomSampleRepositoryImpl(
            @Autowired JPAQueryFactory jpaQueryFactory
    ){
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public SampleEntity SampleQuery() {
        return jpaQueryFactory
                .select(sampleEntity)
                .from(sampleEntity)
                .fetchOne();
    }

    @Override
    public List<FriendListResponseDto> friendList(String email) {

        return jpaQueryFactory.select(
                Projections.constructor(
                        dev.changuii.project.dto.response.FriendListResponseDto.class,
                        userEntity.id,
                        userEntity.name,
                        userEntity.nickname,
                        userEntity.profileImage,
                        userEntity.introduction,
                        userEntity.registerDate,
                        userEntity.userDesignationList))
                .from(friendshipEntity)
                .leftJoin(userEntity)
                .on(friendshipEntity.receiver.email.eq(userEntity.email).and(friendshipEntity.status.eq(FriendshipStatus.FRIEND))).fetch();
    }

    @Override
    public List<FriendRequestResponseDto> friendRequestList(String email) {
        return jpaQueryFactory.select(
                        Projections.constructor(
                                dev.changuii.project.dto.response.FriendRequestResponseDto.class,
                                friendshipEntity.id,
                                userEntity.id,
                                userEntity.name,
                                userEntity.nickname,
                                userEntity.profileImage,
                                userEntity.introduction,
                                userEntity.registerDate,
                                userEntity.userDesignationList))
                .from(friendshipEntity)
                .leftJoin(userEntity)
                .on(friendshipEntity.receiver.email.eq(userEntity.email).and(friendshipEntity.status.eq(FriendshipStatus.REQUESTING))).fetch();
    }


    @Override
    public Slice<FriendListResponseDto> findUserByNickname(SearchUserDto dto) {

        List<FriendListResponseDto> result =  jpaQueryFactory.select(Projections.constructor(
                        dev.changuii.project.dto.response.FriendListResponseDto.class,
                        userEntity.id,
                        userEntity.name,
                        userEntity.nickname,
                        userEntity.profileImage,
                        userEntity.introduction,
                        userEntity.registerDate,
                        userEntity.userDesignationList))
                .from(userEntity)
                .where(userEntity.nickname.contains(dto.getNickname()))
                .offset(dto.getPageable().getOffset())
                .limit(dto.getPageable().getPageSize() + 1 )
                .fetch();


        return checkLastPage(dto.getPageable() ,result);
    }

    // no-offset 방식 처리하는 메서드
    private BooleanExpression lastPostId(Long PostId) {
        if (PostId==null || PostId==0) {
            return null;
        }
//        return post.post_id.lt(PostId);
        return userEntity.id.lt(PostId);
    }

    // 무한 스크롤 방식 처리하는 메서드
    private Slice<FriendListResponseDto> checkLastPage(Pageable pageable, List<FriendListResponseDto> results) {

        boolean hasNext = false;

        //만약 조회한 글이 화면에 보여줄 글보다 갯수가 많으면
        if (results.size() > pageable.getPageSize()) {  //result.size()는 조회한 글 갯수
            //pageable 화면에 보여줄 글 갯수

            hasNext = true;	//다음 글이 있다고 체크
            results.remove(pageable.getPageSize());	//result에 확인용으로 추가한 +1의 글을
            //지워줍니다
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }



}
