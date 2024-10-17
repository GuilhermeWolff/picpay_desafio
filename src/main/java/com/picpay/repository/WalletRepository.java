package com.picpay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picpay.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>{

	Optional<Wallet> findByCpfCnpj(String cpfCnpj);
	Optional<Wallet> findByEmail(String email);

}
