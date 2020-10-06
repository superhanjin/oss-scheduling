package oss;

import oss.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyHandler{

    @Autowired
    SchedulingRepository schedulingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverActivationCancelled_CancelSchedule(@Payload ActivationCancelled activationCancelled){

        if(activationCancelled.isMe()){
            List<Scheduling> schedulingList = schedulingRepository.findByOrderId(activationCancelled.getOrderId());
            for(Scheduling scheduling : schedulingList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                scheduling.setScheduleState("CancelSchedule");
                // view 레파지 토리에 save
                schedulingRepository.save(scheduling);
            }

            System.out.println("##### listener CancelSchedule : " + activationCancelled.toJson());
        }
    }

}
