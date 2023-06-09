## Diagrama de la API
```
/api
├── /alumnos
│   ├── GET: all(void): List<AlumnoModel>
│   └── /{id}
│       ├── GET: one(void): AlumnoModel
│       ├── GET: examenes(void): List<ExamenModel>
│       ├── GET: preguntas(void): List<PreguntaModel>
│       ├── POST: add(AlumnoPostModel): AlumnoModel
│       ├── PUT: edit(AlumnoPostModel): AlumnoModel
│       └── DEL: delete/{id}(Long): HttpStatus
├── /preguntas
│   ├── GET: all(void): List<PreguntaModel>
│   └── /{id}
│       ├── GET: one(void): PreguntaModel
│       ├── POST: add(PreguntaPostModel): PreguntaModel
│       ├── PUT: edit(PreguntaPostModel): PreguntaModel
│       └── DEL: delete/{id}(Long): HttpStatus
└── /examenes
    ├── GET: all(void): List<ExamenModel>
    └── /{id}
        ├── GET: one(void): ExamenModel
        ├── POST: add(ExamenPostModel): ExamenModel
        ├── PUT: edit(ExamenPostModel): ExamenModel
        └── DEL: delete/{id}(Long): HttpStatus
```
