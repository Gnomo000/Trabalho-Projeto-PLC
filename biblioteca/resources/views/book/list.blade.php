@extends('book.layouts.app')

@section('content')
@if (Route::has('login'))
@auth
    <div class="card-header">{{ __('Livros') }}</div>
    <div class="col-lg-1">
    </div>
    <div class="card-body">
        @if (session('status'))
            <div class="alert alert-success" role="alert">
                {{ session('status') }}
            </div>
        @endif
        <div class="col-lg-1">
            <a class="btn btn-success" href="{{ route('book.create') }}">Add</a>
        </div>
        <table class="table table-bordered">
            <tr>
                <th>Id</th>
                <th>Titulo</th>
                <th>Titulo em Inglês.</th>
                <th>Autor</th>
                <th>Edição</th>
                <th>Editora</th>
                <th>Sinopse</th>
                <th>Genero</th>
                <th>Quantidade</th>
                <th>Imagem</th>
                <th>Operações</th>
            </tr>
            @php
                $i = 0;
            @endphp
            @foreach ($books as $book)
                <tr>
                    <td>{{ ++$i }}</td>
                    <td>{{ $book->title }}</td>
                    <td>{{ $book->titleEn }}</td>
                    <td>{{ $book->author }}</td>
                    <td>{{ $book->edition }}</td>
                    <td>{{ $book->publisher }}</td>
                    <td>{{ $book->synopse }}</td>
                    <td>{{ $book->genders }}</td>
                    <td>{{ $book->quantity }}</td>
                    <td><img src="{{$book->image}}" alt="image"  width="120px"></td>
                    <td>
                        <form action="{{ route('book.destroy',$book->id) }}" method="POST">
                            <a class="btn btn-info" href="{{ route('book.show',$book->id) }}">Show</a>
                            <a class="btn btn-primary" href="{{ route('book.edit',$book->id) }}">Edit</a>
                            @csrf
                            @method('DELETE')
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
            @endforeach
        </table>

        

        {{ __('You are logged in!') }}
    </div>

    <div class="hidden fixed top-0 right-0 px-6 py-4 sm:block">
        <a href="{{ url('/home') }}" class="text-sm text-gray-700 dark:text-gray-500 underline">Home</a>
    @else
        <a href="{{ route('login') }}" class="text-sm text-gray-700 dark:text-gray-500 underline">Log in</a>

        @if (Route::has('register'))
            <a href="{{ route('register') }}" class="ml-4 text-sm text-gray-700 dark:text-gray-500 underline">Register</a>
        @endif
    @endauth
</div>
    @endif
@endsection
