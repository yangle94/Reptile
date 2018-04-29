package cn.ylapl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * date: 2018/4/28
 * time: 下午4:33
 * author: Angle
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class TestEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String phone;

    @CreatedDate
    private Date createTime;

    private long createUser;

    @LastModifiedDate
    private Date updateTime;

    private long updateUser;
}