from graphviz import Digraph

# Crear un objeto de diagrama de clases
dot = Digraph(comment='Diagrama de Clases de Aplicación Web de Fútbol')
dot.attr(rankdir='LR')

# Definir clases principales con sus atributos clave
dot.node('Liga', '''Liga
---------------
+ id : String
+ name : String
+ abbreviation : String''')

dot.node('Temporada', '''Temporada
---------------
+ year : Int
+ startDate : Date
+ endDate : Date''')

dot.node('Equipo', '''Equipo
---------------
+ id : String
+ name : String
+ abbreviation : String
+ location : String
+ isActive : Boolean''')

dot.node('Franquicia', '''Franquicia
---------------
+ id : String
+ name : String
+ abbreviation : String
+ isActive : Boolean''')

dot.node('Atleta', '''Atleta
---------------
+ id : String
+ firstName : String
+ lastName : String
+ age : Int
+ jersey : String
+ active : Boolean''')

dot.node('Posicion', '''Posición
---------------
+ id : String
+ name : String
+ abbreviation : String''')

dot.node('Evento', '''Evento
---------------
+ id : String
+ date : Date
+ name : String
+ timeValid : Boolean''')

dot.node('Competicion', '''Competición
---------------
+ id : String
+ date : Date
+ attendance : Int''')

dot.node('Competidor', '''Competidor
---------------
+ id : String
+ homeAway : String
+ winner : Boolean''')

dot.node('Estadio', '''Estadio
---------------
+ id : String
+ fullName : String
+ city : String
+ country : String''')

dot.node('Pais', '''País
---------------
+ id : String
+ name : String
+ abbreviation : String
+ flagHref : String''')

dot.node('Estadistica', '''Estadística
---------------
+ id : String
+ type : String''')

dot.node('CategoriaEstadistica', '''CategoríaEstadística
---------------
+ name : String
+ displayName : String''')

dot.node('EstadisticaDetalle', '''EstadísticaDetalle
---------------
+ name : String
+ displayName : String
+ value : Number''')

dot.node('Transferencia', '''Transferencia
---------------
+ date : Date
+ type : String
+ amount : Float
+ displayAmount : String''')

# Definir relaciones entre las clases
# Liga
dot.edge('Liga', 'Temporada', label='contiene')
dot.edge('Liga', 'Equipo', label='incluye')
dot.edge('Liga', 'Evento', label='organiza')
dot.edge('Liga', 'Pais', label='asociada con')

# Temporada
dot.edge('Temporada', 'Evento', label='incluye')
dot.edge('Temporada', 'Estadistica', label='tiene')

# Franquicia
dot.edge('Franquicia', 'Equipo', label='tiene')
dot.edge('Franquicia', 'Estadio', label='tiene')

# Equipo
dot.edge('Equipo', 'Atleta', label='tiene')
dot.edge('Equipo', 'Pais', label='localizado en')
dot.edge('Equipo', 'Evento', label='compite en')
dot.edge('Equipo', 'Estadio', label='juega en')
dot.edge('Equipo', 'Franquicia', label='pertenece a')

# Atleta
dot.edge('Atleta', 'Equipo', label='pertenece a')
dot.edge('Atleta', 'Posicion', label='juega en')
dot.edge('Atleta', 'Evento', label='participa en')
dot.edge('Atleta', 'Estadistica', label='tiene')
dot.edge('Atleta', 'Pais', label='tiene ciudadanía')
dot.edge('Atleta', 'Transferencia', label='involucrado en')

# Transferencia
dot.edge('Transferencia', 'Atleta', label='involucra')
dot.edge('Transferencia', 'Equipo', label='desde', xlabel='fromTeam')
dot.edge('Transferencia', 'Equipo', label='hacia', xlabel='toTeam')

# Evento
dot.edge('Evento', 'Competicion', label='incluye')
dot.edge('Evento', 'Estadio', label='se lleva a cabo en')

# Competición
dot.edge('Competicion', 'Competidor', label='incluye')

# Competidor
dot.edge('Competidor', 'Equipo', label='es un')

# Estadística
dot.edge('Estadistica', 'CategoriaEstadistica', label='contiene')
dot.edge('CategoriaEstadistica', 'EstadisticaDetalle', label='tiene')

# Visualizar el diagrama
dot.render('diagrama_clases', format='png', cleanup=True)

