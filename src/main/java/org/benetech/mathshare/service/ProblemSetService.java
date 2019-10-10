package org.benetech.mathshare.service;

import org.benetech.mathshare.model.dto.ProblemSetDTO;
import org.benetech.mathshare.model.entity.ProblemSetRevision;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ProblemSetService {

    ProblemSetRevision getLatestProblemSet(long editUrl);

    ProblemSetRevision getProblemSetByShareUrl(long shareUrl);

    ProblemSetRevision saveNewProblemSet(ProblemSetDTO problemSet, String initiator) throws IllegalArgumentException;

    ProblemSetDTO findProblemsByUrlCode(String code) throws IllegalArgumentException;

    Pair<Boolean, ProblemSetRevision> createOrUpdateProblemSet(String code, ProblemSetDTO problemSet, String initiator);

    ProblemSetDTO getLatestProblemSetForEditing(String code);

    String getDefaultProblemSetRevisionCode();

    String getDefaultProblemSetCode();

    List<ProblemSetDTO> findAllExampleProblems();

    List<ProblemSetDTO> findLastNProblemSetsOfUser(String userId, int n);
}
