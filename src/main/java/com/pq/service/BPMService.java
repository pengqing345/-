package com.pq.service;

import com.pq.pojo.BPM;
import com.pq.pojo.BpmTask;

import java.util.List;

public interface BPMService {

    int startBPM(BPM bpm);

    int completeBPM(String HandleName,String startName);

    int refuseBPM(String name);

    List<BpmTask> selectBpm(String name);
}
