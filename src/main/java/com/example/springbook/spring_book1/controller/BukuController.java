package com.example.springbook.spring_book1.controller;

import com.example.springbook.spring_book1.model.Buku;
import com.example.springbook.spring_book1.repository.BukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/buku")
public class BukuController {

    @Autowired
    BukuRepository bukuRepository;

    @GetMapping("/")
    public List<Buku> getAll() {
        return bukuRepository.findAll();
    }

    @PostMapping("/")
    public Buku tambahbuku(@Valid @RequestBody Buku buku) {
        return bukuRepository.save(buku);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Buku> updateBuku(@PathVariable(value = "id") Long id,
                                           @Valid @RequestBody Buku detailbuku) {
        Optional<Buku> buku = bukuRepository.findById(id);
        if (!buku.isPresent())
            return ResponseEntity.notFound().build();
        Buku buku1 = buku.get();
        if (detailbuku.getTitleBook() != null){
            buku1.setTitleBook(detailbuku.getTitleBook());
        }
        if (detailbuku.getNamaDepanPengarang() != null){
            buku1.setNamaDepanPengarang(detailbuku.getNamaDepanPengarang());
        }
        if (detailbuku.getNamaBelakangPengarang() != null){
            buku1.setNamaBelakangPengarang(detailbuku.getNamaBelakangPengarang());
        }
        if (detailbuku.getNamaPeminjam() != null){
            buku1.setNamaPeminjam(detailbuku.getNamaPeminjam());
        }
        if (detailbuku.getStatusPeminjaman() != 0){
            buku1.setStatusPeminjaman(detailbuku.getStatusPeminjaman());
        }
        Buku updatedBuku = bukuRepository.save(buku1);
        return ResponseEntity.ok(updatedBuku);
    }

    @DeleteMapping("/{id}")
    public String deleteBuku(@PathVariable(value = "id") Long id) {
        Optional<Buku> buku = bukuRepository.findById(id);
        String result = "";
        if (!buku.isPresent()) {
            result = "id " + id + " tidak ditemukan";
            return result;
        }
        result = "id " + id + " berhasil di hapus";
        bukuRepository.deleteById(id);
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buku> getBukuById(@PathVariable(value = "id") Long id) {
        Optional<Buku> buku = bukuRepository.findById(id);
        if (!buku.isPresent())
            return ResponseEntity.notFound().build();
        Buku buku1 = buku.get();
        return ResponseEntity.ok().body(buku1);
    }

    @GetMapping("/sortbuku")
    public List<Buku> sortbuku(@RequestParam(value = "title") String titleBook) {
        return bukuRepository.findByTitleBook(titleBook);
    }

    @GetMapping("/sortstatus/{statusPeminjaman}")
    public List<Buku> sortstatus(@PathVariable(value = "statusPeminjaman") int statusPeminjaman) {
        return bukuRepository.findByStatusPeminjaman(statusPeminjaman);

    }

}