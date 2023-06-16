package com.kh.finalPlayTime.service;

import com.kh.finalPlayTime.dto.PlayLikeDto;
import com.kh.finalPlayTime.entity.MemberInfo;
import com.kh.finalPlayTime.entity.PlayInfo;
import com.kh.finalPlayTime.entity.PlayLike;
import com.kh.finalPlayTime.repository.MemberInfoRepository;
import com.kh.finalPlayTime.repository.PlayInfoRepository;
import com.kh.finalPlayTime.repository.PlayLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PlayLikeService {
    private final PlayLikeRepository playLikeRepository;
    private final PlayInfoRepository playInfoRepository;
    private final MemberInfoRepository memberInfoRepository;



// 엔티티로 조회 (회원정보 연극정보의 모든 컬럼이 객체로 조회됨)
    public List<PlayLike> findPlayLikeList (String userId) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setUserId(userId);

        return playLikeRepository.findByMemberInfo(memberInfo);
    }
// 디티오로 조회(원하는 dto 컬럼들만 조회됨)
    public List<PlayLikeDto> findPlayLikeList2(String userId) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setUserId(userId);
        List<PlayLike> playLikes = playLikeRepository.findByMemberInfo(memberInfo);
        List<PlayLikeDto> playLikeDTOs = new ArrayList<>();
        for (PlayLike playLike : playLikes) {
            PlayLikeDto playLikeDTO = new PlayLikeDto();
            playLikeDTO.setPlayLikeId(playLike.getId());
            playLikeDTO.setUserId(playLike.getMemberInfo().getUserId());
            playLikeDTO.setPlayId(playLike.getPlayInfo().getPlayId());
            playLikeDTOs.add(playLikeDTO);
        }
        return playLikeDTOs;
    }
    // 찜등록

    public void addPlayLike(String userId, String playId) {
        PlayLike playLike = new PlayLike();

        // 회원 정보 설정
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setUserId(userId);
        playLike.setMemberInfo(memberInfo);

        // 플레이 정보 설정
        PlayInfo playInfo = new PlayInfo();
        playInfo.setPlayId(playId);
        playLike.setPlayInfo(playInfo);

        playLikeRepository.save(playLike);
    }
}