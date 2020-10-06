package oss;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Scheduling_table")
public class Scheduling {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private String customerName;
    private String address;
    private String phoneNumber;
    private Integer internetCount;
    private Integer tvCount;
    private String equipmentId;
    private String visitDateTime;
    private String scheduleState;

    @PostPersist
    public void onPostPersist(){
        if(this.getScheduleState().equals("InitiateSchedule")){
            System.out.println("#################### InitiateSchedule Command Processed ####################");
        }else {
            System.out.println("#################### Unknown Command is Called ####################");
        }
    }

    @PostUpdate
    public void onPostUpdate(){
        if(this.getScheduleState().equals("Schedule")) {
            Scheduled scheduled = new Scheduled();
            BeanUtils.copyProperties(this, scheduled);
            scheduled.publishAfterCommit();

            System.out.println("#################### Scheduled Event has been published ####################");
        }else if(this.getScheduleState().equals("CancelSchedule")) {
            ScheduleCancelled scheduleCancelled = new ScheduleCancelled();
            //scheduleCancelled.setOrderId(this.getOrderId());
            BeanUtils.copyProperties(this, scheduleCancelled);
            scheduleCancelled.publishAfterCommit();

            System.out.println("#################### ScheduleCancelled Event has been published ####################");
        }else {
            System.out.println("#################### Unknown Command is Called ####################");
        }
    }
/*
    @PrePersist
    public void onPrePersist(){
        if(this.getScheduleState().equals("Schedule")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            this.setVisitDateTime(sdf.format(Calendar.getInstance().getTime()));
        }
    }

 */


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Integer getInternetCount() {
        return internetCount;
    }
    public void setInternetCount(Integer internetCount) {
        this.internetCount = internetCount;
    }
    public Integer getTvCount() {
        return tvCount;
    }
    public void setTvCount(Integer tvCount) {
        this.tvCount = tvCount;
    }
    public String getEquipmentId() {
        return equipmentId;
    }
    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }
    public String getVisitDateTime() {
        return visitDateTime;
    }
    public void setVisitDateTime(String visitDateTime) {
        this.visitDateTime = visitDateTime;
    }
    public String getScheduleState() {
        return scheduleState;
    }
    public void setScheduleState(String scheduleState) {
        this.scheduleState = scheduleState;
    }
}
