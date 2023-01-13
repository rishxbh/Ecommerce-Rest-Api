package com.rishabh.website.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rishabh.website.model.User;
import com.rishabh.website.model.Wishlist;

public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {

	List<Wishlist> findAllByUserOrderByCreatedDateDesc(User user);
	@Query("from wishlist where user_id =?1 and product_id =?2")
	Optional<Wishlist> findWishlist(int userId, int productId);
}
