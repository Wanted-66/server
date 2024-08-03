package dev.changuii.project.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.changuii.project.dto.response.FriendListResponseDto;
import dev.changuii.project.entity.SampleEntity;
import dev.changuii.project.enums.FriendshipStatus;
import dev.changuii.project.repository.CustomSampleRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
    public List<FriendListResponseDto> FriendList(Long userId) {

        return jpaQueryFactory.select(
                Projections.constructor(
                        dev.changuii.project.dto.response.FriendListResponseDto.class,
                        userEntity.id,
                        userEntity.name,
                        userEntity.nickname,
                        userEntity.profileImage,
                        userEntity.introduction,
                        userEntity.registerDate,
                        userEntity.designation))
                .from(friendshipEntity)
                .leftJoin(userEntity)
                .on(friendshipEntity.receiver.id.eq(userEntity.id).and(friendshipEntity.status.eq(FriendshipStatus.FRIEND))).fetch();
    }
}
