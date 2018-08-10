import { Component, OnInit } from '@angular/core';
import { Board } from '../models/board';
import { BoardService } from '../board.service';
import { NgForm } from '@angular/forms';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-board-list',
  templateUrl: './board-list.component.html',
  styleUrls: ['./board-list.component.css']
})
export class BoardListComponent implements OnInit {
  boards: Board[] = [];
  selectedBoard = null;
  closeResult: string;

  loadBoards() {
    this.boardService.index().subscribe(
      data => this.boards = data,
      err => console.log(err)
    );
  }

  createBoard(form: NgForm) {
    const board = new Board();
    board.title = form.value.title;
    board.description = form.value.description;

    this.boardService.create(board).subscribe(
      data => {
        this.loadBoards();

      },
      err => console.log(err)
    );
  }

  deleteBoard(id: number) {
    this.boardService.destroy(id).subscribe(
      (data) => this.loadBoards(),
      (err) => console.log(err)
    );
  }

  // modal methods
  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  constructor(private boardService: BoardService, private modalService: NgbModal, private router: Router) {}

  ngOnInit() {
    this.loadBoards();
  }
}
