package com.guessanimal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "animal_tree", schema = "public")
public class AnimalNode implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String question;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "animal_id")
    private Animal value;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "left_child_id")
    private AnimalNode rightChild;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "right_child_id")
    private AnimalNode leftChild;

}
