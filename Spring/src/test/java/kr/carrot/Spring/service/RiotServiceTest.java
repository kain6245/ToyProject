package kr.carrot.Spring.service;

import kr.carrot.Spring.dto.ChampionMasteryDTO;
import kr.carrot.Spring.dto.MatchReferenceDTO;
import kr.carrot.Spring.dto.SummonerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class RiotServiceTest {

    @Autowired
    private RiotService riotService;

    @Autowired
    private Environment environment;


    @Test
    public void 소환사이름으로조회() {

        // find by name
        SummonerDTO summonerInfoByName = riotService.findSummonerInfoByName(environment.getProperty("riot.summoner-name"));
//        SummonerDTO summonerInfoByName = riotService.findSummonerInfoByName("sheria");

        System.out.println("summonerInfoByName = " + summonerInfoByName);
        assertThat(summonerInfoByName).isNotNull();
    }

    @Test
    public void 소환사아이디로조회() {

        // find by id
        SummonerDTO summonerInfoById = riotService.findSummonerInfoById(environment.getProperty("riot.summoner-id"));

        System.out.println("summonerInfoById = " + summonerInfoById);
        assertThat(summonerInfoById).isNotNull();
    }

    @Test
    public void 챔피언숙련도조회() {

        List<ChampionMasteryDTO> masteryList = riotService.getChampionMasteryBySummonerId(environment.getProperty("riot.summoner-id"));

        assertThat(masteryList).isNotNull();
        masteryList.forEach(System.out::println);
    }

    @Test
    public void 최근게임이력조회() {

        // 최근 5건의 경기 조회
        List<MatchReferenceDTO> matchList = riotService.getMatchList(environment.getProperty("riot.summoner-name"), 5);

        assertThat(matchList).isNotNull();
        matchList.forEach(System.out::println);
    }

}