package com.lisa.myspringbootwithjpa.message.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lisa.myspringbootwithjpa.message.ProducerImpl;
import com.lisa.myspringbootwithjpa.message.transport.TransportAddressDTO;
import com.lisa.myspringbootwithjpa.message.transport.TransportStudentDTO;
import com.lisa.myspringbootwithjpa.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentProducer extends ProducerImpl<TransportStudentDTO> {

    @Autowired
    private ObjectMapper objectMapper;

    public boolean produceMessageForAddStudent(String bindingname,Student student){
        TransportStudentDTO transportStudentDTO= objectMapper.convertValue(student, TransportStudentDTO.class);
        TransportAddressDTO transportAddressDTO= objectMapper.convertValue(student.getAddress(), TransportAddressDTO.class);
        transportStudentDTO.setAddress(transportAddressDTO);

        super.send(bindingname,transportStudentDTO);
        return true;
    }
}
