package com.sangmin.first_board.repository;

import com.sangmin.first_board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface BoardRepository extends CrudRepository<Board,Long>{
    @Override
    ArrayList<Board> findAll();
}
