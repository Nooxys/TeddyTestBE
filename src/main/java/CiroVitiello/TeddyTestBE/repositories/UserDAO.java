package CiroVitiello.TeddyTestBE.repositories;

import CiroVitiello.TeddyTestBE.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO implements JdbcRepository<User> {

//  specifico public.user per fare riferimento allo schema (public) altrimenti non riesce ad accedere alla tabella (POSTGRES)

    private static final String INSERT_USER_QUERY = "INSERT INTO public.user(name,surname,address,location,municipality,province,email,notes) VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE_USER_BY_ID_QUERY = "UPDATE public.user SET name=?, surname=?, address=?, location=?, municipality=?, province=?, email=?, notes=? WHERE id=?";
    private static final String GET_USER_BY_ID_QUERY = "SELECT * FROM public.user WHERE id=?";
    private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM public.user WHERE id=?";
    private static final String GET_USERS_QUERY = "SELECT * FROM public.user";
    private static final String GET_USERS_BY_EMAIL = "SELECT * FROM public.user WHERE email=?";

    @Autowired
    private JdbcTemplate template;



    public RowMapper<User> mapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setAddress(rs.getString("address"));
        user.setLocation(rs.getString("location"));
        user.setMunicipality(rs.getString("municipality"));
        user.setProvince(rs.getString("province"));
        user.setEmail(rs.getString("email"));
        user.setNotes(rs.getString("notes"));

        return user;
    };


    // CRUD OPERATIONS

    @Override
    public List<User> findAll() {

        return template.query(GET_USERS_QUERY, mapper);
    }

    @Override
    public Optional<User> findById(int id) {
        User user = null;
        try {
            user = template.queryForObject(GET_USER_BY_ID_QUERY, new Object[]{id}, mapper);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public User save(User user) {
        template.update(INSERT_USER_QUERY, user.getName(), user.getSurname(), user.getAddress(),user.getLocation(),user.getMunicipality(), user.getProvince(),user.getEmail(),user.getNotes());
        return user;
    }

    @Override
    public User updateById(User user, int id) {
        template.update(UPDATE_USER_BY_ID_QUERY, user.getName(), user.getSurname(), user.getAddress(),user.getLocation(),user.getMunicipality(), user.getProvince(),user.getEmail(),user.getNotes(), id);
        return user;
    }


    @Override
    public void deleteById(int id) {
        template.update(DELETE_USER_BY_ID_QUERY, id);
    }


//    public Optional<User> findByEmail(String email) {
//        User user = null;
//        try {
//            user = template.queryForObject(GET_USERS_BY_EMAIL, new Object[]{email}, mapper);
//        } catch (DataAccessException ex) {
//            System.out.println(ex);
//        }
//        return Optional.ofNullable(user);
//    }

    public List<User> findAllByEmail(String email)  {
        return template.query(GET_USERS_BY_EMAIL, new Object[]{email}, mapper);
    }



}